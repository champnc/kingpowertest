package com.champnc.kingpowertest.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.champnc.kingpowertest.presentation.ui.theme.KingpowertestTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            KingpowertestTheme {
                KingpowerTestGraph()
            }
        }
    }

    @Test
    fun test_card_is_show() {
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule
                .onAllNodesWithContentDescription("card_item")
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onAllNodes(hasContentDescription("card_item")).onFirst().assertIsDisplayed()
    }

    @Test
    fun test_card_click() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 3000) {
                composeTestRule
                    .onAllNodesWithContentDescription("card_item")
                    .fetchSemanticsNodes().size == 1
            }
            onAllNodes(hasContentDescription("card_item")).onFirst().performClick()
            waitForIdle()
            onNode(hasContentDescription("detail_screen_content_description")).assertIsDisplayed()
        }
    }
}