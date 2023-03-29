#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RecaptchaEnterprise, NSObject)

RCT_EXTERN_METHOD(initRecaptchaEnterpriseClient:(NSString)siteKey 
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(execute:(NSString)action
				  withResolver:(RCTPromiseResolveBlock)resolve
				  withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
