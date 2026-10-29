# Notes

## Build fixes applied

- KSP plugin version corrected from `2.2.10-1.0.31` to cached `2.2.10-2.0.2` in `gradle/libs.versions.toml`.
- Retrofit converter usage switched from JakeWharton `asConverterFactory` to Square `KotlinSerializationConverterFactory`. Because the converter class is internal to Kotlin metadata, a small Java helper (`RetrofitConverterFactory`) invokes it.
- Room DAO methods changed from `suspend` to blocking with non-Unit return types (`Long`/`Int`) to avoid KSP/Room/Kotlin 2.2 incompatibilities (`unexpected jvm signature V` and Continuation generic mismatch). Repository wraps DAO calls in `Dispatchers.IO`.
- Verified: `assembleDebug`, `lintDebug`, and `testDebugUnitTest` all pass.

## Status

- App builds successfully.
- Data layer (Room, Retrofit, DataStore) compiles; UI still template (MainActivity shows greeting).
