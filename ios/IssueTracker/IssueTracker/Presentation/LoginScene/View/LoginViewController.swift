//
//  LoginViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/14.
//

import UIKit

final class LoginViewController: UIViewController, ViewModelAdaptable {
    static func create(with viewModel: LoginViewModel) -> UIViewController {
        let viewController = LoginViewController()
        viewController.viewModel = viewModel
        return viewController
    }

    private let loginView = LoginView()
    private var viewModel: LoginViewModel?

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        layout()
        bind()
        loginView.delegate = self
        viewModel?.fetchToken()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func layout() {
        view.addSubview(loginView)

        loginView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
    }

    private func bind() {
        guard let viewModel = viewModel else {
            return
        }

        viewModel.output.isAuthenticated.bind { isAuthenticated in
            if isAuthenticated {
                viewModel.showMainScene()
            }
        }
    }
}

extension LoginViewController: LoginViewDelegate {
    func didClickGitHubLogin() {
        viewModel?.showLoginScene(type: .github)
    }
}
