//
//  MainWireframe.swift
//  iosApp
//
//  Created by Vicente De Miguel on 27/1/22.
//

import UIKit

class MainWireframe {
    
    public var viewController: MainViewController {
        
        // Generating module components
        let viewController: MainViewController = MainViewController(nibName: nil, bundle: nil)
        let viewModel: MainViewModel = createViewModel()
        viewController.set(viewModel: viewModel)
        return viewController
    }
    
    private func createViewModel() -> MainViewModel {
        return MainViewModel()
    }
    
    func present() {
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else { return }
        
        if appDelegate.window == nil {
            appDelegate.window = UIWindow(frame: UIScreen.main.bounds)
        }
        
        let nav = UINavigationController()
        nav.viewControllers = [viewController]
        
        appDelegate.window?.rootViewController = nav
        appDelegate.window?.backgroundColor = .white
        appDelegate.window?.makeKeyAndVisible()
    }
}
