package net.hwyz.iov.mp.app.base

interface MviActionProcessor<A : MviAction, R : MviResult> {
    suspend fun executeAction(action: A): R
}