![GitHub Release](https://img.shields.io/github/v/release/CommBank-PowerBoard/powerboard-android-mobile-sdk)
[![](https://www.jitpack.io/v/CommBank-PowerBoard/powerboard-android-mobile-sdk.svg)](https://www.jitpack.io/#CommBank-PowerBoard/powerboard-android-mobile-sdk)

# Project Description

The CommBank-Powerboard Android MobileSDK seamlessly integrates with CommBank-Powerboard services to easily build a payments
flow in your Android app. The SDK provides a customisable experience with pre-built UI widgets that
handle and support various payment methods.

Once you have setup and initialised the MobileSDK in your application, you can use the MobileSDK
widgets to access payment flows. These include interacting with GooglePay, Paypal, Afterpay and Click To Pay. You can also
complete 3DS challenges, capture addresses, securely collect gift card details, and tokenise card
details.

# Requirements

- Android 7.0 (API level 24) and above

# How to install and configure the SDK

1. Setup the CommBank-Powerboard API Integration by [contacting CommBank-Powerboard](https://www.commbank.com.au/business/payments/take-online-payments/powerboard.html#support) to
   signup for a pre-production account, and then following
   our [integration guide](https://developer.powerboard.commbank.com.au/reference/mobile-sdk-initialise) .
2. [Setup](https://developer.powerboard.commbank.com.au/reference/mobile-sdk-installation#setup-the-powerboard-android-sdk)
   the iOS or Android SDK.
3. [Configure](https://developer.powerboard.commbank.com.au/reference/mobile-sdk-installation#step-1-configure-repository-access)
   repository access.
4. [Add](https://developer.powerboard.commbank.com.au/reference/mobile-sdk-installation#step-2-add-sdk-dependency)
   the SDK dependency to your app.
5. Use the following guide
   to [initialize](https://developer.powerboard.commbank.com.au/reference/mobile-sdk-initialise#initialize-the-android-sdk)
   your SDK.

# Getting Started (Sample App)

This repository includes a sample application (`sample/`) that demonstrates how to integrate and use the Powerboard Android Mobile SDK.
This example app showcases various features of the SDK and provides a practical guide for developers looking to implement Powerboard
payments in their own Android applications.

## Prerequisites

Before you can run the sample app, you'll need to have the following installed:

*   **Powerboard Integration** Ensure you have setup your Powerboard account and signed up for your Pre-Production account. (As indicated in [step 1](#how-to-install-and-configure-the-sdk))
*   **Android Studio:** The latest stable version of Android Studio is recommended. You can download it from the [official Android Studio website](https://developer.android.com/studio).
*   **Android SDK:** Ensure you have the necessary Android SDK platforms and build tools installed via the Android Studio SDK Manager.
*   **Git:** You'll need Git to clone the repository.

## Configuration

The sample app uses a `config.properties` file to manage environment-specific settings. Each build flavor has a corresponding `config.properties` file containing key-value pairs used during the build process. These values are injected into the app via `build.gradle`.

**Note:**
> You only need to create and configure the `config.properties` file for the flavors you are actively working on. For example, if you are working on `preprod` and `prod`, you can omit the file for `staging`, as it is not required for your current build.
> Ensure the necessary configuration files are in place before running the app to avoid missing variable errors.

### `config.properties` File

1.  **Flavors:** The sample app supports three flavors: `staging`, `preprod` and `prod`. You'll need to provide values for each flavor.
2.  **Location:** Create a file named `config.properties` in the `sample/src/[flavor]` directory of the project.
3.  **Structure:** The `config.properties` file should contain key-value pairs for each configuration setting.
4.  **Required Fields:** The following fields are required in the `config.properties` file:

*   **`API_ACCESS_TOKEN`:** The Powerboard API access token for the specified environment (e.g., preprod, staging, production). This token is used by the sample app to make direct calls to the Powerboard API for tasks like creating customers or managing transactions.
*   **`WIDGET_ACCESS_TOKEN`:** The Powerboard Widget/UI access token for the specified environment. This token is used by the Powerboard SDK to authenticate and authorize the use of the pre-built UI widgets for payment processing.
*   **`GATEWAY_ID_MPGS`:** Your Powerboard service ID for the MPGS (Mastercard Payment Gateway Services) gateway. This ID is required to process card payments, handle 3D Secure (3DS) authentication, and manage other card-related transactions.
*   **`GATEWAY_ID_PAY_PAL`:** Your Powerboard service ID for the PayPal gateway. This ID is necessary to enable PayPal as a payment method within the sample app.
*   **`GATEWAY_ID_AFTER_PAY`:** Your Powerboard service ID for the Afterpay gateway. This ID is required to enable Afterpay as a payment method.
*   **`GATEWAY_ID_CLICK_TO_PAY`:** Your Powerboard service ID for the ClickToPay gateway. This ID is required to enable ClickToPay as a payment method.
*   **`GATEWAY_ID_GOOGLE_PAY`:** Your Powerboard service ID for the Google Pay gateway. This ID is required to enable Google Pay as a payment method.
*   **`MERCHANT_IDENTIFIER`:** Your Powerboard merchant identifier, which is required for Google Pay. This identifier is used to associate your transactions with your Google Pay merchant account.

**Example `config.properties`:**
```
# Authentication keys
API_ACCESS_TOKEN=your_api_access_token 
WIDGET_ACCESS_TOKEN=your_widget_access_token 
# Gateway keys
GATEWAY_ID_MPGS=your_gateway_id_mpgs 
GATEWAY_ID_PAY_PAL= your_gateway_id_pay_pal 
GATEWAY_ID_AFTER_PAY= your_gateway_id_after_pay 
GATEWAY_ID_CLICK_TO_PAY= your_gateway_id_click_to_pay 
GATEWAY_ID_GOOGLE_PAY= your_gateway_id_google_pay 
# Misc Gateway keys
MERCHANT_IDENTIFIER= your_merchant_identifier 
```

**Note:**
> Replace the placeholder values (e.g., `your_api_access_token`) with your actual keys and IDs.

## Running the Sample App

1.  **Clone the Repository:** bash git clone https://github.com/CommBank-PowerBoard/powerboard-android-mobile-sdk.git
2.  **Open in Android Studio:** Open the `sample/` directory in Android Studio.
3.  **Select a Build Variant:** In Android Studio, go to `Build` > `Select Build Variant...` and choose one of the following:
*   `stagingDebug`
*   `preprodDebug`
*   `prodDebug`
4.  **Run the App:** Click the "Run" button (green play icon) in Android Studio to build and run the sample app on an emulator or a connected device.
