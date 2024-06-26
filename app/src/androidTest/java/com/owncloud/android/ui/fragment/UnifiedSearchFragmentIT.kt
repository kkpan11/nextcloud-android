/*
 * Nextcloud - Android Client
 *
 * SPDX-FileCopyrightText: 2020 Tobias Kaminsky <tobias@kaminsky.me>
 * SPDX-FileCopyrightText: 2020 Nextcloud GmbH
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.owncloud.android.ui.fragment

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.nextcloud.test.TestActivity
import com.owncloud.android.AbstractIT
import com.owncloud.android.datamodel.OCFile
import com.owncloud.android.lib.common.SearchResultEntry
import com.owncloud.android.ui.unifiedsearch.UnifiedSearchSection
import com.owncloud.android.ui.unifiedsearch.UnifiedSearchViewModel
import org.junit.Rule
import org.junit.Test
import java.io.File

class UnifiedSearchFragmentIT : AbstractIT() {
    @get:Rule
    val testActivityRule = IntentsTestRule(TestActivity::class.java, true, false)

    @Test
    fun showSearchResult() {
        val activity = testActivityRule.launchActivity(null)
        val sut = UnifiedSearchFragment.newInstance(null, null)

        activity.addFragment(sut)

        shortSleep()

        UiThreadStatement.runOnUiThread {
            sut.onSearchResultChanged(
                listOf(
                    UnifiedSearchSection(
                        providerID = "files",
                        name = "Files",
                        entries = listOf(
                            SearchResultEntry(
                                "thumbnailUrl",
                                "Test",
                                "in Files",
                                "http://localhost/nc/index.php/apps/files/?dir=/Files&scrollto=Test",
                                "icon",
                                false
                            )
                        ),
                        hasMoreResults = false
                    )
                )
            )
        }
        shortSleep()
    }

    @Test
    fun search() {
        val activity = testActivityRule.launchActivity(null) as TestActivity
        val sut = UnifiedSearchFragment.newInstance(null, null)
        val testViewModel = UnifiedSearchViewModel(activity.application)
        testViewModel.setConnectivityService(activity.connectivityServiceMock)
        val localRepository = UnifiedSearchFakeRepository()
        testViewModel.setRepository(localRepository)

        val ocFile = OCFile("/folder/test1.txt").apply {
            storagePath = "/sdcard/1.txt"
            storageManager.saveFile(this)
        }

        File(ocFile.storagePath).createNewFile()

        activity.addFragment(sut)

        shortSleep()

        UiThreadStatement.runOnUiThread {
            sut.setViewModel(testViewModel)
            sut.vm.setQuery("test")
            sut.vm.initialQuery()
        }
        shortSleep()
    }
}
