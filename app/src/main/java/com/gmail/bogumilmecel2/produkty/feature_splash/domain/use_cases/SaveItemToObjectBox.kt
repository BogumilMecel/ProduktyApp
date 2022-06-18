package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import android.util.Log
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.common.util.TAG
import com.gmail.bogumilmecel2.produkty.common.util.extensions.toObjectBoxItem
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem


class SaveItemToObjectBox(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke(items: List<Item>): Result {
        val objectItems = mutableListOf<ObjectBoxItem>()
        items.forEach {
            objectItems.add(it.toObjectBoxItem())
        }
        val result =  itemsRepository.saveItemsToObjectBox(objectItems)
        Log.e(TAG,result.toString())
        return result
    }
}