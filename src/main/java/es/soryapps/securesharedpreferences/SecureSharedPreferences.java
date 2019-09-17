/*
 * Copyright (C) 2019 SoryApps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.soryapps.securesharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Set;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class SecureSharedPreferences implements SharedPreferences
{
    private static final String PREFS_ID_PREFIX = "SECSP";

    private SharedPreferences preferences;

    public SecureSharedPreferences(@NonNull Context context, @NonNull String preferencesId)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {  preferences = getPreferencesMplus(context, preferencesId);  }
        else
        {  preferences = getPreferencesMinf(context, preferencesId);  }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private SharedPreferences getPreferencesMplus(@NonNull Context context, @NonNull String preferencesId)
    {
        String masterKeyAlias;

        try
        {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            return EncryptedSharedPreferences.create(
                    (PREFS_ID_PREFIX + preferencesId),
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        }
        catch(GeneralSecurityException|IOException e)
        {
            // Alt: Log.e()
            throw new SecurityException("Error using SecureSharedPreferences (start)");
        }
    }

    private SharedPreferences getPreferencesMinf(@NonNull Context context, @NonNull String preferencesId)
    {  return (SharedPreferences) new SecurePreferences(context, preferencesId);  }

    // Note Android minor M (API 23): Not supported yet. You can propose an implementation.
    @Override
    public Map<String, ?> getAll()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {  return preferences.getAll();  }
        return null;
    }

    @Nullable
    @Override
    public String getString(@NonNull String key, @Nullable String defValue)
    {  return preferences.getString(key, defValue);  }

    @Nullable
    @Override
    public Set<String> getStringSet(@NonNull String key, @Nullable Set<String> defValues)
    {  return preferences.getStringSet(key, defValues);  }

    @Override
    public int getInt(@NonNull String key, int defValue)
    {  return preferences.getInt(key, defValue);  }

    @Override
    public long getLong(@NonNull String key, long defValue)
    {  return preferences.getLong(key, defValue);  }

    @Override
    public float getFloat(@NonNull String key, float defValue)
    {  return preferences.getFloat(key, defValue);  }

    @Override
    public boolean getBoolean(@NonNull String key, boolean defValue)
    {  return preferences.getBoolean(key, defValue);  }

    @Override
    public boolean contains(@NonNull String key)
    {  return preferences.contains(key);  }

    @Override
    public Editor edit()
    {  return preferences.edit();  }

    @Override
    public void registerOnSharedPreferenceChangeListener(@NonNull OnSharedPreferenceChangeListener listener)
    {  preferences.registerOnSharedPreferenceChangeListener(listener);  }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(@NonNull OnSharedPreferenceChangeListener listener)
    {  preferences.unregisterOnSharedPreferenceChangeListener(listener);  }
}
