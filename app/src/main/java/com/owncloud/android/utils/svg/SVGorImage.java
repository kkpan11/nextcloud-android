/*
 * Nextcloud - Android Client
 *
 * SPDX-FileCopyrightText: 2021 Tobias Kaminsky <tobias@kaminsky.me>
 * SPDX-FileCopyrightText: 2021 Nextcloud GmbH
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package com.owncloud.android.utils.svg;

import android.graphics.Bitmap;

import com.caverock.androidsvg.SVG;

public class SVGorImage {
    private SVG svg;
    private Bitmap bitmap;

    public SVGorImage(SVG svg, Bitmap bitmap) {
        this.svg = svg;
        this.bitmap = bitmap;
    }

    public SVG getSVG() {
        return svg;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
