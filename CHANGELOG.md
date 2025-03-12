# Changelog

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

