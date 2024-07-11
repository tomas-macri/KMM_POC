//
//  MainViewControllar.swift
//  iosApp
//
//  Created by Vicente De Miguel on 27/1/22.
//

import UIKit
import Combine
import shared

class MainViewController: UIViewController {
    
    private var viewModel: MainViewModel?
    var cancellables: Set<AnyCancellable> = []
    var messages: [String] = []
    
    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var btReset: UIButton!
    
    @IBOutlet weak var btGetData: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        responseViewModel()
        config()
        viewModel?.loadDatabase()
    }
    
    private func config() {
        self.tableView.estimatedRowHeight = 44.0
        self.tableView.rowHeight = UITableView.automaticDimension
        tableView.register(UINib(nibName: MessageCell.MessageCellNibName, bundle: nil), forCellReuseIdentifier: MessageCell.MessageCellReuseIdentifier)
    }
    
    private func responseViewModel() {
        viewModel?.$messagesList.sink { [weak self] messages in
            guard let messages = messages else { return }
            self?.messages = messages
            self?.tableView.reloadData()
        }.store(in: &cancellables)
    }
    
    //MARK: - Actions
    
    @IBAction func tapReset(_ sender: Any) {
        viewModel?.tapReset()
    }
    
    @IBAction func tapMessage(_ sender: Any) {
        viewModel?.tapMessage()
    }
    
    func set(viewModel: MainViewModel) {
        self.viewModel = viewModel
    }
}

extension MainViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.messages.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: MessageCell.MessageCellReuseIdentifier)
            as? MessageCell else {
                return MessageCell()
        }
        cell.lbMessage.text = self.messages[indexPath.row]
        return cell
    }
    
    
}

extension MainViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}




