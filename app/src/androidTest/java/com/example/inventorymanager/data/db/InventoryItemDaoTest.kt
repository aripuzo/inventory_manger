package com.example.inventorymanager.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventorymanager.data.model.InventoryItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InventoryItemDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: InventoryItemDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.inventoryDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testCRUDOperations() = runBlocking {
        // Create
        val item = InventoryItem(
            id = 1,
            name = "Item 1",
            totalStock = 10,
            price = 100.0,
            description = "Description for item 1"
        )
        dao.insert(item)

        // Read
        var result = dao.getItem(1)
        assertEquals(item, result)

        // Update
        val updatedItem = item.copy(name = "Updated Item 1")
        dao.update(updatedItem)
        result = dao.getItem(1)
        assertEquals(updatedItem, result)

        // Delete
        dao.delete(updatedItem)
        result = dao.getItem(1)
        assertNull(result)
    }
}