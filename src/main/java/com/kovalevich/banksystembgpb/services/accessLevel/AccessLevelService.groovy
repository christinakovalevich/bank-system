package com.kovalevich.banksystembgpb.services.accessLevel

import com.kovalevich.banksystembgpb.models.accessLevel.AccessLevel
import com.kovalevich.banksystembgpb.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccessLevelService extends LoggerService {

    List<AccessLevel> findAll()

    AccessLevel findById(short id)

    AccessLevel findByLabel(String label)

    AccessLevel save(AccessLevel accessLevel)

    AccessLevel update(short id, AccessLevel accessLevel)

    def delete(short id)

}
