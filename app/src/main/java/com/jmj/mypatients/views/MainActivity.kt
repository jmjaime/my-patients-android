package com.jmj.mypatients.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jmj.domain.domainModule
import com.jmj.inmemoryrepository.inMemoryRepositoryModule
import com.jmj.mypatients.R
import com.jmj.mypatients.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        startKoin {

            androidContext(this@MainActivity)
                modules(listOf(domainModule, inMemoryRepositoryModule, viewModule))
        }
    }
}
