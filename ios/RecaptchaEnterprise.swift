import RecaptchaEnterprise
@objc(RecaptchaEnterprise)
class RecaptchaEnterprise: NSObject {
	var recaptchaClient: RecaptchaClient?
	
	@objc(initRecaptchaEnterpriseClient:withResolver:withRejecter:)
	func initRecaptchaEnterpriseClient(siteKey: String, resolve:@escaping  RCTPromiseResolveBlock,reject:@escaping RCTPromiseRejectBlock) -> Void {
		Recaptcha.getClient(siteKey: siteKey) { client, error in
			guard let client = client else {
				reject("error","RecaptchaClient creation error: \(error?.errorMessage ?? "unknow").", nil)
				print(error?.errorMessage)
				return
			}
			self.recaptchaClient = client
			resolve("RecaptchaClient creation success")
		}
	}
	@objc(execute:withResolver:withRejecter:)
	func execute(action:String, resolve:@escaping  RCTPromiseResolveBlock,reject:@escaping RCTPromiseRejectBlock) -> Void{
		
		guard let recaptchaClient = recaptchaClient else {
			print("RecaptchaClient creation failed.")
			return
		}
		var actionName : RecaptchaActionType = .other
		if(action == "login"){
			actionName = .login
		}else if(action == "signup"){
			actionName = .signup
		}
		
		recaptchaClient.execute(RecaptchaAction(action: actionName)) { token, error in
			if let token = token {
				resolve(token.recaptchaToken)
				print(token.recaptchaToken)
			} else {
				reject("error",error?.errorMessage,nil)
				print(error?.errorMessage)
			}
		}
	}
	
}
