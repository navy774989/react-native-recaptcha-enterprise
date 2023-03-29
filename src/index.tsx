import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-recaptcha-enterprise' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const RecaptchaEnterprise = NativeModules.RecaptchaEnterprise
  ? NativeModules.RecaptchaEnterprise
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function initRecaptchaEnterpriseClient(
  siteKey: string
): Promise<number> {
  return RecaptchaEnterprise.initRecaptchaEnterpriseClient(siteKey);
}

export function execute(action: 'signup' | 'login' | 'other'): Promise<number> {
  return RecaptchaEnterprise.execute(action);
}
