package io.aethibo.pxlart.domain.curated

import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.aethibo.pxlart.core.data.repositories.MainRepository
import io.aethibo.pxlart.core.entities.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class CuratedUseCase(private val repository: MainRepository) : ICuratedUseCase {

    private var currentSearchResult: Flow<PagingData<Photo>>? = null

    /*
     * Get curated photos
     */
    override fun getCuratedPhotos(scope: CoroutineScope): Flow<PagingData<Photo>> {
        val lastResult = currentSearchResult

        if (lastResult != null) return lastResult

        val newResult: Flow<PagingData<Photo>> =
            repository.getCuratedPhotos().cachedIn(scope)

        currentSearchResult = newResult

        return newResult
    }
}