//
//  MessageCell.swift
//  iosApp
//
//  Created by Vicente De Miguel on 1/2/22.
//

import UIKit

class MessageCell: UITableViewCell {
    static let MessageCellNibName = "MessageCell"
    static let MessageCellReuseIdentifier = "MessageCellReuseIdentifier"
    
    @IBOutlet weak var lbMessage: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
}
