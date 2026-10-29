package com.nectarofthepeople.spectoroster.data.remote;

import kotlinx.serialization.json.Json;
import okhttp3.MediaType;
import retrofit2.Converter;
import retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;

public final class RetrofitConverterFactory {
    private RetrofitConverterFactory() {}

    public static Converter.Factory create(Json json, MediaType contentType) {
        return KotlinSerializationConverterFactory.create(json, contentType);
    }
}