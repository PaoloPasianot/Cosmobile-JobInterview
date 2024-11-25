package it.paolopasianot.cosmobile.util

import android.util.Log
import androidx.profileinstaller.ProfileVerifier
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

/**
 * Logs the app's Baseline Profile Compilation Status using [ProfileVerifier].
 *
 * When delivering through Google Play, the baseline profile is compiled during installation.
 * In this case you will see the correct state logged without any further action necessary.
 * To verify baseline profile installation locally, you need to manually trigger baseline
 * profile installation.
 *
 * For immediate compilation, call:
 * ```bash
 * adb shell cmd package compile -f -m speed-profile com.example.macrobenchmark.target
 * ```
 * You can also trigger background optimizations:
 * ```bash
 * adb shell pm bg-dexopt-job
 * ```
 * Both jobs run asynchronously and might take some time complete.
 *
 * To see quick turnaround of the ProfileVerifier, we recommend using `speed-profile`.
 * If you don't do either of these steps, you might only see the profile status reported as
 * "enqueued for compilation" when running the sample locally.
 *
 * @see androidx.profileinstaller.ProfileVerifier.CompilationStatus.ResultCode
 */
class ProfileVerifierLogger {
    companion object {
        private const val TAG = "ProfileInstaller"
    }

    operator fun invoke() = MainScope().launch {
        val status = ProfileVerifier.getCompilationStatusAsync().await()
        Log.d(TAG, "Status code: ${status.profileInstallResultCode}")
        Log.d(
            TAG,
            when {
                status.isCompiledWithProfile -> "App compiled with profile"
                status.hasProfileEnqueuedForCompilation() -> "Profile enqueued for compilation"
                else -> "Profile not compiled nor enqueued"
            },
        )
    }
}
