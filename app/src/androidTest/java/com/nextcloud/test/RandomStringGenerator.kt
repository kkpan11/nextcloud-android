/*
 * Nextcloud - Android Client
 *
 * SPDX-FileCopyrightText: 2022 Álvaro Brey <alvaro@alvarobrey.com>
 * SPDX-FileCopyrightText: 2022 Nextcloud GmbH
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.nextcloud.test

object RandomStringGenerator {
    private const val DEFAULT_LENGTH = 8
    private val ALLOWED_CHARACTERS = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    @JvmOverloads
    @JvmStatic
    fun make(length: Int = DEFAULT_LENGTH): String {
        return (1..length)
            .map { ALLOWED_CHARACTERS.random() }
            .joinToString("")
    }
}
