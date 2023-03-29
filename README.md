# react-native-recaptcha-enterprise

recaptcha enterprise

## Installation

```sh
npm install react-native-recaptcha-enterprise
```

## Usage

```js
import {
  initRecaptchaEnterpriseClient,
  execute,
} from 'react-native-recaptcha-enterprise';

// ...

await initRecaptchaEnterpriseClient('siteKey');
const result = await execute('login');
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
