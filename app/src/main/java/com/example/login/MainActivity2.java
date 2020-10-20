package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.HomePage.ExampleData;
import com.example.login.HomePage.FragmentEngineering;
import com.example.login.HomePage.FragmentMBA;
import com.example.login.HomePage.MaxViewedRecyclerViewAdapter;
import com.example.login.HomePage.ProductDetails.ProductDetails;
import com.example.login.HomePage.RecyclerViewAdapter;
import com.example.login.HomePage.SwitchView.SwitchView;
import com.example.login.SellWithLearnweel.MyOrdersMainActivity;
import com.example.login.SellWithLearnweel.SellWithLearnweel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hotchemi.android.rate.AppRate;


//  this code is to go to notification activity. use this wherever u want
//  startActivity(new Intent(MainActivity2.this,NotificationActivity.class));



public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private NavigationMenuPresenter navigationView;
    GoogleSignInClient mGoogleSignInClient;
    TextView textCartItemCount;
    int mCartItemCount = 6;

    Button Logout;
    SharedPreferences sharedPreferences;



    //declaring recycycler view, adapter and layout manager for popular contents
    private RecyclerView popularRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    //declaring recycycler view, adapter and layout manager for max viewed contents
    private RecyclerView maxRecyclerView;
    private MaxViewedRecyclerViewAdapter mMaxAdapter;
    private RecyclerView.LayoutManager mMaxLayoutmanager;

    private ArrayList<ExampleData> popular;
    private ArrayList<ExampleData> max;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        Logout = findViewById(R.id.nav_logout);



        // -------------------- HOME PAGE------------------------------------





        popular = new ArrayList<>();
        max = new ArrayList<>();

        searchView = findViewById(R.id.searchView);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling search activity
                Intent intent = new Intent(MainActivity2.this, SwitchView.class);
                startActivity(intent);
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling search activity
                Intent intent = new Intent(MainActivity2.this, SwitchView.class);
                startActivity(intent);
            }
        });

        //setting up view pager for engineering and mba fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout talentsTitles = (TabLayout) findViewById(R.id.titles);
        addTabs(viewPager);
        talentsTitles.setupWithViewPager(viewPager);


        popularRecyclerView = findViewById(R.id.popularRecyclerView);
        mLayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);//LinearLayoutManager.HORIZONTAL, true
        mAdapter = new RecyclerViewAdapter(popular);

        popularRecyclerView.setLayoutManager(mLayoutmanager);
        popularRecyclerView.setAdapter(mAdapter);

        //load product details page on clicking an item
        mAdapter.setOnClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //pass data of the selected item through intent
                ExampleData productDetails = popular.get(position);

                Intent intent=new Intent(MainActivity2.this, ProductDetails.class);
                intent.putExtra("productID",productDetails.getid());
                intent.putExtra("productImageURL",productDetails.getUrl());
                intent.putExtra("productTitle",productDetails.getTitle());
                intent.putExtra("productRating",String.valueOf(productDetails.getRating()));
                intent.putExtra("productPrice",String.valueOf(productDetails.getPrice()));
                startActivity(intent);
            }
        });

        //declaring a volley queue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

        //json data url
        String urlaaa = "https://run.mocky.io/v3/1136660a-7cde-4c47-84bf-13eb6236aafe";

        //make json request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlaaa,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("popularContent");
                            for(int i =0; i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //Toast.makeText(MainActivity2.this, obj.toString(), Toast.LENGTH_LONG).show();
                                String ImageURL = obj.getString("image");
                                final int id = obj.getInt("id");
                                final String Title = obj.getString("title");
                                final String Author = obj.getString("author");
                                final int Price = (Integer) obj.get("price");
                                final double rating = (Double) obj.get("rating");
                                boolean fav = ((Integer)obj.get("fav") == 1);

                                popular.add(new ExampleData(id,ImageURL,Title,Author,Price, (float) rating,false));
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity2.this, "error in parsing json", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Error in getting data", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


        maxRecyclerView = findViewById(R.id.maxViewedRecyclerView);
        mMaxLayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);//LinearLayoutManager.HORIZONTAL, true
        mMaxAdapter = new MaxViewedRecyclerViewAdapter(max);

        maxRecyclerView.setLayoutManager(mMaxLayoutmanager);
        maxRecyclerView.setAdapter(mMaxAdapter);
        //load product details page on clicking an item
        mMaxAdapter.setOnClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //pass data of the selected item through intent
                ExampleData productDetails = max.get(position);

                Intent intent=new Intent(MainActivity2.this, ProductDetails.class);
                intent.putExtra("productID",productDetails.getid());
                intent.putExtra("productImageURL",productDetails.getUrl());
                intent.putExtra("productTitle",productDetails.getTitle());
                intent.putExtra("productRating",String.valueOf(productDetails.getRating()));
                intent.putExtra("productPrice",String.valueOf(productDetails.getPrice()));
                startActivity(intent);
            }
        });

        JsonObjectRequest jsonObjectRequestmax = new JsonObjectRequest(Request.Method.GET, urlaaa,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("maxViewed");
                            for(int i =0; i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //Toast.makeText(MainActivity2.this, obj.toString(), Toast.LENGTH_LONG).show();
                                String ImageURL = obj.getString("image");
                                final int id = obj.getInt("id");
                                final String Title = obj.getString("title");
                                final String Author = obj.getString("author");
                                final int Price = (Integer) obj.get("price");
                                final double rating = (Double) obj.get("rating");
                                boolean fav = ((Integer)obj.get("fav") == 1);

                                max.add(new ExampleData(id,ImageURL,Title,Author,Price, (float) rating,false));
                                mMaxAdapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity2.this, "error in parsing json", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Error in getting data", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequestmax);


        Button explore = findViewById(R.id.maxViewedExploreMore);
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //explore more functionality comes here
            }
        });





        // --------------------------------------------------------



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity2.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //tabview
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentEngineering(), "Engineering");
        adapter.addFrag(new FragmentMBA(), "MBA");
        viewPager.setAdapter(adapter);
    }

    public void notification(View view) {
        startActivity(new Intent(MainActivity2.this,NotificationActivity.class));
    }

    //tab view adapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    // Navigation Drawer items

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //noinspection SwitchStatementWithoutDefaultBranch
        switch (item.getItemId()) {
            case R.id.nav_orders:
                Intent intent1 = new Intent(MainActivity2.this, MyOrdersMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_referral:
                Intent i = new Intent (MainActivity2.this,referral.class);
                startActivity(i);
                break;
            case R.id.nav_rate_us:
                AppRate.with(this)
                        .setInstallDays(1)
                        .setLaunchTimes(4)
                        .setRemindInterval(2)
                        .monitor();

                AppRate.showRateDialogIfMeetsConditions(this);
                AppRate.with(this).showRateDialog(this);
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=com.kpshopy.learnweel&hl=en_IN");
                Intent i1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(i1);
                break;
           case R.id.nav_rewards:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                      ///  new myrewards()).commit();
                startActivity(new Intent(MainActivity2.this,Referral1.class));
                break;

            case R.id.nav_sell:
               startActivity(new Intent(MainActivity2.this, SellWithLearnweel.class));
                break;

            case R.id.nav_settings:
                startActivity(new Intent(MainActivity2.this,Settings.class));
                break;
            case R.id.nav_policy:
                startActivity(new Intent(MainActivity2.this,Policy.class));
                break;
            case R.id.nav_about_us:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                  //      new aboutus()).commit();
                startActivity(new Intent(MainActivity2.this,AboutUs.class));
                break;
            case R.id.nav_Support:
                startActivity(new Intent(MainActivity2.this,HelpSupportActivity.class));
                break;
          /*  case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new contactus()).commit();
                break;*/
            case R.id.nav_share:
                Intent myintent = new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String shareBody ="Wanna Buy Books at affordable rate , check this app out . Enjoy Shopping  https://play.google.com/store/apps/details?id=com.kpshopy.learnweel";
                myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myintent,"Share Using"));
                break;
           case R.id.nav_logout:

               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString(getResources().getString(R.string.prefLoginState),"logout");
               editor.clear();
               editor.apply();
               startActivity(new Intent(MainActivity2.this,MainActivity3.class));
               finish();

                /*mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity2.this,"Successfully Logged Out",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                                finish();
                            }
                        });*/
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public void linkedin(View view) {
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse("https://www.linkedin.com/in/learnweel-060471190/"));

        startActivity(intent);

    }

    public void twitter(View view) {
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse("https://twitter.com/learnweel/"));

        startActivity(intent);
    }
    public void yt(View view) {
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse( "https://www.youtube.com/channel/UCJovdddeY1eRHfcZ_IJ6Rtw/featured"));

        startActivity(intent);
    }

    public void fb(View view) {
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse( "https://m.facebook.com/learnweel/"));

        startActivity(intent);
    }

    public void insta(View view) {
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse("https://instagram.com/learnweel?igshid=gfh0raynmgsr/"));

        startActivity(intent);
    }

    public void profile(View view) {
        startActivity(new Intent(MainActivity2.this,myacc.class));
    }

    public void wishlist(View view) {
        Intent intent = new Intent(MainActivity2.this,wishlist.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOptionsItemSelected(menuItem);
                 Intent in=new Intent(MainActivity2.this,cart_activity.class);
                 startActivity(in);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_cart: {
                // Do something
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}