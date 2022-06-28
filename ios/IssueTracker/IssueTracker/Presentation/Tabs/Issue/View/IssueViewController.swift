//
//  IssueViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import SnapKit
import UIKit

protocol IssueViewDelegate: AnyObject {
    func didClickFilterButton()
}

class IssueViewController: UIViewController {
    private let tableView = UITableView(frame: .zero, style: .plain)
    private let dataSource = TableviewDataSource()
    weak var delegate: IssueViewDelegate?

    private let leftButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "line.3.horizontal.decrease.circle"), for: .normal)
        button.setTitle("필터", for: .normal)
        button.setTitleColor(UIColor.primary, for: .normal)
        return button
    }()

    private let rightButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "checkmark.circle"), for: .normal)
        button.setTitle("선택", for: .normal)
        button.setTitleColor(UIColor.primary, for: .normal)
        button.semanticContentAttribute = .forceRightToLeft
        return button
    }()

    private let searchController = UISearchController()

    override func viewDidLoad() {
        super.viewDidLoad()
        layout()
        configureView()
        registerAction()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        view.backgroundColor = .white
        delegate = self

        navigationItem.title = "이슈"
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: leftButton)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: rightButton)
        navigationController?.navigationBar.prefersLargeTitles = true

        tableView.dataSource = dataSource
        tableView.delegate = self
        tableView.register(IssueListCell.self, forCellReuseIdentifier: IssueListCell.identifier)
    }

    private func layout() {
        view.addSubview(tableView)

        tableView.snp.makeConstraints {
            $0.top.bottom.leading.trailing.equalTo(view.safeAreaLayoutGuide)
        }
    }

    private func registerAction() {
        leftButton.addAction(.init(handler: { [weak self] _ in
            self?.delegate?.didClickFilterButton()
        }), for: .touchUpInside)
    }
}

extension IssueViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: false)
    }

    func scrollViewWillBeginDragging(_: UIScrollView) {
        navigationItem.searchController = searchController
    }
}

extension IssueViewController: IssueViewDelegate {
    func didClickFilterButton() {
        let netxtViewController = FilterViewController()
        netxtViewController.modalPresentationStyle = UIModalPresentationStyle.automatic

        present(netxtViewController, animated: true, completion: nil)
    }
}
