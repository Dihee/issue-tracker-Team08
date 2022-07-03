//
//  ViewModelAdaptable.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/30.
//

import UIKit

protocol ViewModelAdaptable {
    associatedtype ViewModel
    static func create(with viewModel: ViewModel) -> UIViewController
}
