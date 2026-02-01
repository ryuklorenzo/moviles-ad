package ies.sequeros.dam.pmdp.vista

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object IODispatchers {
    actual val io: CoroutineDispatcher= Dispatchers.IO

}