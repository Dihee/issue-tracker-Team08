//
//  IssueCreateViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/29.
//

import SnapKit
import UIKit

class IssueCreateViewController: UIViewController {
    private let saveButton: TextButton = {
        let button = TextButton()
        let symbolConfiguration = UIImage.SymbolConfiguration(pointSize: 14)
        button.setSymbol(UIImage(systemName: "plus", withConfiguration: symbolConfiguration), on: .trailing)
        button.setTitle("저장", for: .normal)
        button.setTitleColor(UIColor.gray, for: .normal)
        return button
    }()

    private let segmentedControl: UISegmentedControl = {
        let items = ["마크다운", "미리보기"]
        var segmentedControl = UISegmentedControl(items: items)
        segmentedControl.selectedSegmentIndex = 1
        segmentedControl.tintColor = .white
        segmentedControl.backgroundColor = .graybg
        return segmentedControl
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        configureView()
    }

    override func viewWillDisappear(_: Bool) {
        super.viewDidDisappear(false)
        segmentedControl.isHidden = true
        navigationController?.navigationBar.prefersLargeTitles = true
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        navigationItem.rightBarButtonItem = .init(customView: saveButton)
        navigationController?.navigationBar.addSubview(segmentedControl)
        navigationController?.navigationBar.prefersLargeTitles = false

        segmentedControl.snp.makeConstraints {
            $0.centerX.centerY.equalToSuperview()
            $0.width.equalTo(200)
            $0.height.equalTo(35)
        }
    }
}
