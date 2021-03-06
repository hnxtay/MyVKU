/*
 * Copyright (c) 2020 My VKU by tsnAnh
 */

package dev.tsnanh.vku.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import dev.tsnanh.vku.R
import dev.tsnanh.vku.databinding.ActivityNewsBinding
import dev.tsnanh.vku.utils.Constants

/**
 * An Activity with webview for display news when user's device doesn't have browser
 */
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        val navArgs: NewsActivityArgs by navArgs()

        // load content
        binding.webView.apply {
            loadUrl(Constants.DAO_TAO_URL + navArgs.url)
            setTitle(navArgs.title)
        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }
}
