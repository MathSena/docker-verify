package com.mathsena.dockerverify.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DockerService {

  private final DockerClient dockerClient;

  public DockerService(DockerClient dockerClient) {
    this.dockerClient = dockerClient;
  }

  public List<Container> listContainers(boolean showAll) {
    log.info("Listing containers with showAll={}", showAll);
    return dockerClient.listContainersCmd().withShowAll(showAll).exec();
  }

  public List<Image> listImages() {
    log.info("Listing images");
    return dockerClient.listImagesCmd().exec();
  }

  public void startContainer(String containerId) {
    log.info("Starting container with id={}", containerId);
    dockerClient.startContainerCmd(containerId).exec();
  }

  public void stopContainer(String containerId) {
    log.info("Stopping container with id={}", containerId);
    dockerClient.stopContainerCmd(containerId).exec();
  }

  public void removeContainer(String containerId) {
    log.info("Removing container with id={}", containerId);
    dockerClient.removeContainerCmd(containerId).exec();
  }

  public void createContainer(String imageId) {
    log.info("Creating container with imageId={}", imageId);
    dockerClient.createContainerCmd(imageId).exec();
  }

  public List<Image> filterImages(String filterName) {
    log.info("Filtering images with filterName={}", filterName);
    return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
  }

  public void deleteContainer(String id) {
    log.info("Deleting container with id={}", id);
    dockerClient.removeContainerCmd(id).exec();
  }
}
