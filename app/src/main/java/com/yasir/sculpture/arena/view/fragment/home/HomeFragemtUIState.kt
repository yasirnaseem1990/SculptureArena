package com.yasir.sculpture.arena.view.fragment.home

sealed class HomeFragmentUIState

object LoadingState : HomeFragmentUIState()
object LoadingNextPageState : HomeFragmentUIState()
object ContentState : HomeFragmentUIState()
object ContentNextPageState : HomeFragmentUIState()
class ErrorState(val message: String?) : HomeFragmentUIState()
class ErrorNextPageState(val message: String?) : HomeFragmentUIState()
