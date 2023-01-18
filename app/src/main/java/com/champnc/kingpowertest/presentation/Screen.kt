package com.champnc.kingpowertest.presentation

const val DETAIL_ARGUMENT = "url"

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = "main")
    object DetailScreen: Screen(route = "detail/url?={url}") {
        fun withArgument(url: String): String {
            return "detail/url?=$url"
        }
    }
}