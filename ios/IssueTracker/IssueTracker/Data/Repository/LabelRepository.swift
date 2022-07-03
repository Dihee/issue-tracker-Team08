//
//  LabelRepository.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/30.
//

import Foundation

protocol LabelRepository {
    func fetchLabels(completion: @escaping () -> Void)
}

final class DefaultLabelRepository: LabelRepository {
    // TODO: URLCache 활용하여 네트워크 결과 캐싱
    func fetchLabels(completion _: @escaping () -> Void) {
        // Step.1 Storage 에서 캐쉬 가져오기
        // Step.2 Service 를 통해 새로운 데이터 갱신
        // Step.3 ViewModel 에 갱신된 데이터 전달
        // Step.4 데이터 캐싱
    }
}
