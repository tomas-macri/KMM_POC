//
//  MainViewModel.swift
//  iosApp
//
//  Created by Vicente De Miguel on 27/1/22.
//

import Foundation
import Combine
import shared

class MainViewModel  {

    @Published var messagesList: [String]?
    
    func loadDatabase() {
        var messages: [String] = []
        let database = UseCaseProvider().quotesUseCaseBuild()
        database.invoke { dataList, error in
            if let dataList = dataList {
                _ = dataList.map({messages.append($0.message)})
            }
            self.messagesList = messages
        }
        
    }
    
    func tapReset() {
        let reset = UseCaseProvider().resetQuotesUseCaseBuild()
        reset.invoke { kotlinUnit, error in
            self.messagesList?.removeAll()
        }
    }
    
    func tapMessage() {
        var messages: [String] = []
        let database = UseCaseProvider().getAnotherQuoteUseCaseBuild()
        database.invoke { dataList, error in
            if let dataList = dataList {
                _ = dataList.map({messages.append($0.message)})    
            }
            self.messagesList = messages
        }
        
    }
    
//    func hola() {
//
//        let b = UseCaseProvider().anotherQuotesUseCaseBuild()
//       // let a = UseCaseProvider().quotesUseCaseBuild()
//
//        b.invoke { listado, error in
//            print(listado)
//        }
//
//
//        //let b = UseCaseProvider().resetQuotesUseCaseBuild()
//        //let c = UseCaseProvider().anotherQuotesUseCaseBuild()
//        print("hola")
//    }
//
 
}


