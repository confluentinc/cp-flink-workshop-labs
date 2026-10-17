# Change Log

## Unreleased

### Features and Fixes

- Renumber lab directories to match the workshop's reordered labs: `lab-02` becomes `lab-01`, `lab-03` becomes `lab-02`, `lab-05` becomes `lab-04`
- `lab-02/resources/flink-application.yaml` targets the `flink-demo` cluster's `default` FlinkEnvironment and the `cmf-rest-class` CMFRestClass in `flink`
- Remove `compute-pool.json`. Lab 02 uses the compute pool the GitOps repo already declares.
- Lab 04's Tekton tasks point at the renumbered `lab-04/resources/ci-app` path and the `default` FlinkEnvironment

## v0.0.0 - YYYY-MM-DD

### Features and Fixes

- Initial release
