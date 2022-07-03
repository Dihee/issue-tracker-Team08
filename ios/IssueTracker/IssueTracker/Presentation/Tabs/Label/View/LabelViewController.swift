//
//  LabelViewController.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class LabelViewController: UIViewController, ViewModelAdaptable {
    static func create(with viewModel: LabelViewModel) -> UIViewController {
        let viewController = LabelViewController()
        viewController.viewModel = viewModel
        return viewController
    }

    private lazy var addButton: UIButton = {
        let symbolConfiguration = UIImage.SymbolConfiguration(pointSize: 14)
        let symbol = UIImage(systemName: "plus", withConfiguration: symbolConfiguration)
        let button = TextButton()

        button.setTitle("추가", for: .normal)
        button.setSymbol(symbol, on: .trailing)
        button.addAction(.init(handler: self.showNextScene), for: .touchUpInside)

        return button
    }()

    private let tableView = UITableView()

    private var viewModel: LabelViewModel?

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
        bind()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func bind() {
        guard let viewModel = viewModel else {
            return
        }

        viewModel.output.labels.bind(to: self) { [weak self] _ in
            self?.tableView.reloadData()
        }
    }

    // MARK: - UI Configuration
    private func configureUI() {
        view.backgroundColor = .systemBackground
        configureNavigationBar()
        configureTableView()
    }

    private func configureNavigationBar() {
        navigationItem.title = "레이블"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.rightBarButtonItem = .init(customView: addButton)
    }

    private func configureTableView() {
        view.addSubview(tableView)
        tableView.frame = view.frame
        tableView.autoresizingMask = [
            .flexibleBottomMargin,
            .flexibleLeftMargin,
            .flexibleRightMargin,
            .flexibleTopMargin,
            .flexibleWidth,
            .flexibleHeight
        ]

        tableView.dataSource = self
        tableView.register(LabelCell.self, forCellReuseIdentifier: LabelCell.reuseIdentifier)
    }
}

// MARK: - Table view data source
extension LabelViewController: UITableViewDataSource {
    func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        viewModel?.output.count ?? 20
    }

    func tableView(_ tableView: UITableView, cellForRowAt _: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LabelCell.reuseIdentifier) as? LabelCell else {
            return UITableViewCell()
        }

        cell.setTitle("Documentation", color: .secondary3)
        cell.setDescription("개발 내용을 문서화 한 이슈에 사용하는 레이블")

        return cell
    }
}

// MARK: - Action handlers
extension LabelViewController {
    func showNextScene(_: UIAction) {
        viewModel?.action.showNextScene()
    }
}
