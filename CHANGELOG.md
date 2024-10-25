# Changelog

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

