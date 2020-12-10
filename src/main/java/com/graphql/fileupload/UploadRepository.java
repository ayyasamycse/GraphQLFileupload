package com.graphql.fileupload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphql.entity.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Integer>{

}
