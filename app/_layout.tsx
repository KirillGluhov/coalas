import { useFonts } from "expo-font";
import { SplashScreen, Stack } from "expo-router";
import { useEffect } from "react";

SplashScreen.preventAutoHideAsync();

export default function RootLayout() {
  const [loaded, error] = useFonts({
    'GothamPro': require('../assets/fonts/GothamPro-Regular.ttf'),
    'GothamPro-Bold': require('../assets/fonts/GothamPro-Bold.ttf')
  })

  useEffect(() => {
    if (loaded || error)
    {
      SplashScreen.hideAsync();
    }
  },[loaded, error])

  if (!loaded && !error)
  {
    return null;
  }
  
  return <Stack>
    <Stack.Screen 
      name="index" 
      options={{
        title: 'Registration',
          headerShown: false,
        }}
      />
  </Stack>;
}
