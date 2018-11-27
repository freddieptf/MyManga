package com.freddieptf.malry.di

import com.freddieptf.malry.detail.ChapterListFragment
import com.freddieptf.malry.library.LibraryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by freddieptf on 11/17/18.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun dataProviderComponent(module: DataProviderModule): DataProviderComponent

}