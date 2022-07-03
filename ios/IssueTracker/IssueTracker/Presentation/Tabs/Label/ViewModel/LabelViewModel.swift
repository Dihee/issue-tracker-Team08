//
//  LabelViewModel.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/29.
//

import Foundation

protocol LabelViewModelInput {
    func viewLoaded()
}

protocol LabelViewModelOutput {
    var labels: Observable<[String]> { get }
    var count: Int { get }
}

typealias LabelViewModelDataFlow = LabelViewModelInput & LabelViewModelOutput

protocol LabelViewModel {
    var input: LabelViewModelInput { get }
    var output: LabelViewModelOutput { get }
    var action: LabelViewModelAction { get }
}

protocol LabelViewModelAction {
    func showNextScene()
}

struct DefaultLabelViewModel: LabelViewModel, LabelViewModelDataFlow {
    var input: LabelViewModelInput { self }
    var output: LabelViewModelOutput { self }
    let action: LabelViewModelAction

    let labels: Observable<[String]> = Observable([])
    var count: Int = 0

    private let repository: LabelRepository

    init(action: LabelViewModelAction, repository: LabelRepository) {
        self.action = action
        self.repository = repository
    }

    func viewLoaded() {
        repository.fetchLabels {}
    }

    func getItem(at indexPath: IndexPath) -> String? {
        labels.value[indexPath.row]
    }
}
