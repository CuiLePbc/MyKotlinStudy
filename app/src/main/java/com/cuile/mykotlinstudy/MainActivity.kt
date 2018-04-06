package com.cuile.mykotlinstudy

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.cuile.mykotlinstudy.intfac.DataItemInterface
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfoResultData
import com.cuile.mykotlinstudy.toutiao.vandp.TouTiaoDetailActivity
import com.cuile.mykotlinstudy.toutiao.vandp.TouTiaoFragment
import com.cuile.mykotlinstudy.wechat.data.WeChatInfoResultData
import com.cuile.mykotlinstudy.wechat.vandp.WeChatDetailActivity
import com.cuile.mykotlinstudy.wechat.vandp.WeChatFragment
import com.cuile.mykotlinstudy.yike.vandp.YiKeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {


    override fun onFragmentInteraction(itemData: DataItemInterface) {
        when(itemData) {
            is TouTiaoInfoResultData -> startActivity<TouTiaoDetailActivity>(ItemDetailActivity.ITEM_URL to itemData.url)
            is WeChatInfoResultData -> startActivity<WeChatDetailActivity>(ItemDetailActivity.ITEM_URL to itemData.url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbar
        setSupportActionBar(toolbar)

        // fab button
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // 侧滑菜单监听
        nav_view.setNavigationItemSelectedListener(this)

        addToutiao()

    }

    fun addToutiao() {
        title = getString(R.string.toutiao)
        // 初始化头条fragment
        var toutiaoFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_toutiao_fragment))
        if (toutiaoFragment == null) {
            toutiaoFragment = TouTiaoFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main_container, toutiaoFragment, getString(R.string.tag_toutiao_fragment)).commit()
        }

    }

    fun addWeChat() {
        var weChatFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_wechat_fragment))
        if (weChatFragment == null) {
            weChatFragment = WeChatFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main_container, weChatFragment, getString(R.string.tag_wechat_fragment)).commit()
        }
        title = getString(R.string.wechat)
    }

    fun addYiKe() {
        var yiKeFragment = supportFragmentManager.findFragmentByTag(getString(R.string.tag_yike_fragment))
        if (yiKeFragment == null) {
            yiKeFragment = YiKeFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.main_container, yiKeFragment, getString(R.string.tag_yike_fragment)).commit()
        }
        title = getString(R.string.smile)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_toutiao -> {
                addToutiao()
            }
            R.id.nav_wechat -> {
                addWeChat()
            }
            R.id.nav_smile -> {
                addYiKe()
            }
            R.id.nav_share -> {

            }
            R.id.nav_setting -> {

            }

        }


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
