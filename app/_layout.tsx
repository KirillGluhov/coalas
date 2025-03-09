import { useFonts } from "expo-font";
import { SplashScreen, Stack } from "expo-router";
import { createServer } from "miragejs";
import { useEffect } from "react";

createServer({
  routes() {
    this.post("/login", (schema, request) => {
      let loginCredentials = JSON.parse(request.requestBody)

      if ('password' in loginCredentials && ('phone' in loginCredentials || 'email' in loginCredentials))
      {
        return {
            accessToken: "piuy6478",
            refreshToken: "adryg132454",
            userType: "employee"
        }
      }
      else
      {
        return {
          status: "error",
        }
      }
    })

  },
})


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
        title: 'Login',
          headerShown: false,
        }}
      />
      <Stack.Screen 
        name="main" 
        options={{
          title: 'Main',
            headerShown: false,
          }}
      />
      <Stack.Screen 
        name="registration" 
        options={{
          title: 'Registration',
            headerShown: false,
          }}
      />
  </Stack>;
}
