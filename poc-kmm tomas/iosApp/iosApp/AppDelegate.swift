//
//  AppDelegate.swift
//  iosApp
//
//  Created by Vicente De Miguel on 26/1/22.
//

import UIKit
import shared

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        KoinKt.doInitKoin()
        
        MainWireframe().present()
        return true
    }


}

