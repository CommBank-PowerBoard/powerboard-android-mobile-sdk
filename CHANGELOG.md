# Changelog

## [4.1.0] - 2025-10-14

### Changed
- Implemented native PayPal checkout flow using the latest PayPal SDK (`2.0.0`)
- Updated to use PayPal's native compose button; removed reliance on web views for PayPal
- Updated Compose BOM to `2025.06.01`
- Updated internal dependencies (AndroidX, Koin, coroutines, etc.)
- General improvements to `AddressWidget` (input UX and defaults)

### Fixed
- Generic error handling for network failures across payment methods
- Cardholder name validation issues (including hyphen handling and invalid characters)
- Gift Card PIN length validation
- Flows resuming correctly after app interruptions
- Fallback behavior when no external browser is available
- Afterpay webview stuck due to double‑tap during loading
- Click to Pay: repeated "No Internet Connection" toasts
- Address search: "No address found" incorrectly populating input; country name validation; keyboard casing defaults
- Google Pay: misleading setup error messages
- PayPal: activity state management with "Don't keep activities" enabled (prevents re-launch loops)

### Removed
- All WebView dependencies related to PayPal checkout

## [4.0.0] - 2025-07-17

### Added
- New customisations for all Widgets with `Appearance` added to all Widget contracts
- Material Theming inheritance

### Changed
- `Config` param standardised to all Widget contracts
- Handling of token callback results for Wallets now within SDK processing

# Fixed
- Afterpay disabled state not working correctly

## [3.0.0] - 2025-04-02

### Changed
- Updated ThreeDSWidget to more specific Integrated3DS with updated event handling
- Updated dependencies and gradle version to `8.8.2`
- Content descriptions for implied buttons (Used by accessibility TalkBack)
- Replaced `HyperlinkText` with `LinkText` composable
- `PayPalDataCollectorUtil` calling function from `collectDeviceInfo()` to `collectDeviceId`

### Fixed
- SDK Theming consistency (removed scale factor, device size)
- `LinkText` tappable area to use `minHeight = 24.dp` for better accessibility
- `SearchTextField` structure to use column instead of dropdown for keyboard accessibility
- Updated network version to `1.3.0` containing fix for different API error response mappings
- Google Pay payment request to use correct gateway

### Removed
- `verticalScroll()` from `AddressDetailsWidget`

## [2.0.0] - 2025-03-12

### Added
- New `CardDetailsWidgetConfig` to manage card details
- Supported card scheme functionality (optional)
- Card Scheme detection using schema file
- `PayPalSavePaymentSourceWidget` - PayPal Vault widget
- `AccountScreen` as an example for collection of PayPal Vault token and creating of Customer
- New `loadingDelegate` field added to `CardDetailsWidget`, `PayPalWidget` and `PayPalSavePaymentSourceWidget`
- Environment flavors to sample app (`staging`, `preprod` & `prod`))
- Background theme to all widgets
- Missing outline colour scheme to `StyleScreen`

### Changed
- Card scheme list matching supported schemes
- Card security code to match web (CSC > CID)
- Updated font scaling (input fields and buttons)
- Internal UI state (state = action) for widgets
- Icon to `PayPalVaultConfig` to apply custom icon or none
- Logic to validate card input on empty state
- Separated `Theme.cornerRadius` into 2 parts (`textFieldCornerRadius` & `buttonCornerRadius`)
- Refactored `ThreeDSWidget` to `Integrated3DSWidget`
- Updated gradle version (`8.8.2`) and dependencies
- Renamed `accessToken` to `widgetAccessToken` used by the SDK
- Updated client-sdk version for web specific widgets to `v1.116.4`
- SDK Theme colour scheme (accessibility compliant)

### Fixed
- Double capture in sample app when using PayPal
- Crash on `StyleScreen` dimension counters allowing < 0 values

### Removed
- Settings screen from sample app
- `secretKey` functionality in place of `apiAccessToken`

## [1.2.0] - 2024-10-01

### Added

- `enableTestMode` flag to `MobileSDK` initialisation
- Autofill feature to `CardDetailsWidget` and `AddressWidget`
- Web Activity for PayPal flow

### Changed

- Updated NetworkLib to `1.1.0`

### Fixed

- PayPal redirect Url

### Removed

- Payment Workflow placeholder
- `SdkBottomSheet` embedded in widgets (3DS, PayPal)

## [1.1.1] - 2024-08-07

### Added

- Initial stages for Payment Workflow
- Access token functionality
- Function to validate if SDK is initialised

### Changed

- Moved network logic into separate dependency module
- Card widget input field error labels
- Consent text (for accessibility)
- Downgraded Compose BOM dependency (2023.10.01)
- Removed `publicKey` functionality, in place of `accessToken`
- Renamed "MastercardSRC" to "ClickToPay"

## [1.1.0] - 2024-07-27

### Added

- Initial Powerboard Android MobileSDK release
- Widgets for checkout integration (Card Tokenisation, PayPal, Google Pay, 3DS, Address)

