package ies.sequeros.dam.pmdp.vista

import kotlinx.coroutines.CoroutineDispatcher

expect object IODispatchers {
    val io: CoroutineDispatcher
}