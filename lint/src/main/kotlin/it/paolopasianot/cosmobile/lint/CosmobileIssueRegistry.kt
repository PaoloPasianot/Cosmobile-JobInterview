package it.paolopasianot.cosmobile.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import it.paolopasianot.cosmobile.lint.designsystem.DesignSystemDetector

class CosmobileIssueRegistry : IssueRegistry() {

  override val issues = listOf(
    DesignSystemDetector.ISSUE,
      TestMethodNameDetector.FORMAT,
      TestMethodNameDetector.PREFIX,
  )

  override val api: Int = CURRENT_API

  override val minApi: Int = 12

  override val vendor: Vendor = Vendor(
    vendorName = "Cosmobile",
    feedbackUrl = "https://github.com/PaoloPasianot/Cosmobile-Android/issues",
    contact = "https://github.com/PaoloPasianot/Cosmobile-Android",
  )
}
