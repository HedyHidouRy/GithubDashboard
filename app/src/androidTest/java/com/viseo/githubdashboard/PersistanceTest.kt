package com.viseo.githubdashboard

import androidx.room.Room
import androidx.test.runner.AndroidJUnit4
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.providers.local.GithubDao
import com.viseo.githubdashboard.data.providers.local.GithubRoomDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest


val roomTestModule = module(definition = {
    single {
        Room.inMemoryDatabaseBuilder(get(), GithubRoomDatabase::class.java)
            .build().githubDao()
    }

})

@RunWith(AndroidJUnit4::class)
class PersistanceTest : KoinTest {

    val githubDao: GithubDao by inject()

    @Before
    fun before() {
        loadKoinModules(roomTestModule)
    }


    @After
    fun after() {

    }

    @Test
    fun testSave() {
        val user = User(1, "Hedy", "http://mylogo.com")

        githubDao.insertUser(user)
        val fetchedUser = githubDao.getUser("Hedy")

        assertEquals(user, fetchedUser)
    }
}
