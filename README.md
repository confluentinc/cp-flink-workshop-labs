# cp-flink-workshop-labs

This repository holds the public lab assets for the Confluent Platform for Apache Flink Workshop, available internally at [confluentinc/cp-flink-workshop](https://github.com/confluentinc/cp-flink-workshop).

## Overview

Raw lab assets (Kubernetes manifests, configs, and application source) for the hands-on labs in the [cp-flink-workshop](https://github.com/confluentinc/cp-flink-workshop) repository — a workshop on deploying and operating **Confluent Platform for Apache Flink** on Kubernetes.

Lab instructions, slide decks, themes, and build tooling live in `cp-flink-workshop`. This repository holds only the files attendees apply, build, or deploy during the labs.

## Platform Versions

See the [CMF versions and interoperability](https://docs.confluent.io/cp-flink/current/installation/versions-interoperability.html) page for supported versions.

## Repository Layout

Assets are grouped by the lab that uses them, following the workshop's lab numbering:

| Directory | Used by | Contents |
|-----------|---------|----------|
| `lab-01/resources/` | Lab 01 - Install CP Flink by Hand | kind cluster config, Postgres, CFK/CMF platform manifests |
| `lab-02/resources/` | Lab 02 - Stand Up the Sandbox and Deploy Your First Workloads | FlinkApplication manifest |
| `lab-04/resources/` | Lab 04 - Build an End-to-End CI Pipeline | `ci-app/` Maven Flink job, Avro schema, Tekton pipeline and tasks |

Lab 02 also bootstraps the `flink-demo` cluster from the [confluent-platform-gitops](https://github.com/osowski/confluent-platform-gitops) repository. Lab 03 has no assets yet.

The contract with the workshop is that lab instructions reference these files with paths relative to this repository's root (e.g., `kubectl apply -f lab-01/resources/postgres.yaml`).

## Contributions

This repository is part of the Confluent organization on GitHub. It is public and open to contributions from the community.

## License

Please see the LICENSE file for contribution terms.

## Updates and Changes

Please see the CHANGELOG.md for details of recent updates.