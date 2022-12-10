/*
 * Copyright 2019 Uriah Shaul Mandel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bald.uriah.baldphone.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ColorInt;

import com.bald.uriah.baldphone.R;

public class D {
    public static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";
    public static final String WHATSAPP_PROFILE_MIMETYPE = "vnd.android.cursor.item/vnd.com.whatsapp.profile";
    public static final int
            MILLISECOND = 1,
            SECOND = 1000 * MILLISECOND,
            MINUTE = 60 * SECOND,
            HOUR = 60 * MINUTE,
            DAY = 24 * HOUR;
    public static final int vibetime = 100;
    public static final String BALD_PREFS = BPrefs.KEY;//default device settings
    @ColorInt
    public static final int DEFAULT_STATUS_BAR_COLOR = Color.BLACK;
    public final static View.OnClickListener longer = v -> Toast.makeText(v.getContext(), R.string.press_longer, Toast.LENGTH_LONG).show();
    public static final View.OnClickListener EMPTY_CLICK_LISTENER = v -> {
    };
    public static final int LOW_BATTERY_LEVEL = 20;
    public static final String NOT_NUMBERS_REGEX = "[^0123456789]";

    //nope.
    private D() {
    }

    public static class Days {
        public static final int SUNDAY = 0b1;
        public static final int MONDAY = 0b10;
        public static final int TUESDAY = 0b100;
        public static final int WEDNESDAY = 0b1000;
        public static final int THURSDAY = 0b10000;
        public static final int FRIDAY = 0b100000;
        public static final int SATURDAY = 0b1000000;
        public static final int ALL = SUNDAY | MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY;
        public static final int[] ARRAY_ALL = new int[]{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
    }
}
