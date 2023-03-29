import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import {
  initRecaptchaEnterpriseClient,
  execute,
} from 'react-native-recaptcha-enterprise';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    (async () => {
      initRecaptchaEnterpriseClient('').then(() => {
        execute('login').then(setResult);
      });
    })();
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'white',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
