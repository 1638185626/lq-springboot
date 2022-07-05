//package com.example.lqactiviti701.controller;
//
//import com.baomidou.mybatisplus.extension.api.R;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.activiti.bpmn.converter.BpmnXMLConverter;
//import org.activiti.bpmn.model.BpmnModel;
//import org.activiti.editor.constants.ModelDataJsonConstants;
//import org.activiti.editor.language.json.converter.BpmnJsonConverter;
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngines;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.repository.Model;
//import org.activiti.engine.repository.ModelQuery;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.PNGTranscoder;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.CharSetUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * @className: ModelerController
// * @description: TODO 类描述
// * @author: qing liu
// * @date: 2022/6/18
// **/
//@RestController
////@RequestMapping("/activiti/modeler")
//public class ModelerController{
//    private static final Logger LOGGER = LoggerFactory.getLogger(ModelerController.class);
//
//    @Autowired
//    private RepositoryService repositoryService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    /**
//     * 模型列表
//     */
//    @GetMapping("/list")
//    public R list(ModelEntityImpl modelEntity) {
//        ModelQuery modelQuery = repositoryService.createModelQuery();
//        modelQuery.orderByLastUpdateTime().desc();
//        // 条件过滤
//        if (StringUtils.isNotBlank(modelEntity.getKey())) {
//            modelQuery.modelKey(modelEntity.getKey());
//        }
//        if (StringUtils.isNotBlank(modelEntity.getName())) {
//            modelQuery.modelNameLike("%" + modelEntity.getName() + "%");
//        }
//        Integer pageNum = 1;
//        Integer pageSize = 10;
//        List<Model> resultList = modelQuery.listPage((pageNum - 1) * pageSize, pageSize);
//        Page<ModelEntityImpl> list = new Page<>();
//        List<ModelEntityImpl> rows = new ArrayList<>();
//        resultList.parallelStream().forEach(model -> {
//            ModelEntityImpl modelEntity1 = (ModelEntityImpl) model;
//            rows.add(modelEntity1);
//        });
//        list.setRecords(rows);
//        list.setTotal(modelQuery.count());
//        list.setCurrent(pageNum);
//        list.setSize(pageSize);
//        return R.ok(list);
//    }
//
//    /**
//     * 创建模型
//     */
//    @PostMapping(value = "/create")
//    public R create(@RequestParam("name") String name, @RequestParam("key") String key,
//                             @RequestParam(value = "description", required = false) String description) {
//        String id = "";
//        try {
//            //初始化一个空模型
//            Model model = repositoryService.newModel();
//            //设置一些默认信息
//            int revision = 1;
//            ObjectNode modelNode = objectMapper.createObjectNode();
//            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
//            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
//            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
//            model.setName(name);
//            model.setKey(key);
//            model.setMetaInfo(modelNode.toString());
//            repositoryService.saveModel(model);
//            id = model.getId();
//            //完善ModelEditorSource
//            ObjectNode editorNode = objectMapper.createObjectNode();
//            editorNode.put("id", "canvas");
//            editorNode.put("resourceId", "canvas");
//            ObjectNode stencilSetNode = objectMapper.createObjectNode();
//            stencilSetNode.put("namespace",
//                    "请假.bpmn20.xml");
//            editorNode.putPOJO("stencilset", stencilSetNode);
//            repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
//            return R.ok( "创建模型成功" + id);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return R.failed("创建模型失败");
//    }
//
//
//
//    /**
//     * 保存流程定义数据
//     */
//    @PutMapping(value = "/{modelId}/save")
//    public void saveModel(@PathVariable String modelId, @RequestParam("name") String name, @RequestParam("json_xml") String json_xml,
//                          @RequestParam("svg_xml") String svg_xml, @RequestParam("description") String description) {
//        try {
//            Model model = repositoryService.getModel(modelId);
//
//            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
//
//            modelJson.put("model_name", name);
//            modelJson.put("model_description", description);
//            model.setMetaInfo(modelJson.toString());
//            model.setName(name);
//            model.setVersion(model.getVersion() + 1);
//            repositoryService.saveModel(model);
//
//            repositoryService.addModelEditorSource(model.getId(), Objects.requireNonNull(json_xml.getBytes(StandardCharsets.UTF_8)));
//
//            InputStream svgStream = new ByteArrayInputStream(Objects.requireNonNull(svg_xml.getBytes(StandardCharsets.UTF_8)));
//            TranscoderInput input = new TranscoderInput(svgStream);
//
//            PNGTranscoder transcoder = new PNGTranscoder();
//            // Setup output
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            TranscoderOutput output = new TranscoderOutput(outStream);
//
//            // Do the transformation
//            transcoder.transcode(input, output);
//            final byte[] result = outStream.toByteArray();
//            repositoryService.addModelEditorSourceExtra(model.getId(), result);
//            outStream.close();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error saving model", e);
//        }
//    }
//
//    /**
//     * 根据Model部署流程
//     */
//    @GetMapping(value = "/deploy/{modelId}")
//    public R deploy (@PathVariable("modelId") String modelId){
//        try {
//            Model modelData = repositoryService.getModel(modelId);
//            if (modelData == null) {
//                return R.failed("modelId is entity");
//            }
//            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
//            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);
//            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
//            if (model == null) {
//                return R.failed("model 不能能为空");
//            }
//            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
//            byte[] bpmnBytes = bpmnXMLConverter.convertToXML(model,"UTF-8");
//            String processName = "BPMN/"  + modelData.getName() + ".bpmn20.xml";
//            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
//            LOGGER.info("部署成功，部署ID=" + deployment.getId());
//            return R.ok("部署成功");
//        } catch (Exception e) {
//            LOGGER.error("根据模型部署流程失败：modelId={}", modelId, e);
//        }
//        return R.failed("部署失败");
//    }
//
//    /**
//     * 导出model的xml文件
//     */
//    @GetMapping(value = "/export/{modelId}")
//    public void export (@PathVariable("modelId") String modelId, HttpServletResponse response){
//        try {
//            Model modelData = repositoryService.getModel(modelId);
//            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
//            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
//            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
//            // 流程非空判断
//            if (!CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
//                BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
//                byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
//                ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
//                String filename = bpmnModel.getMainProcess().getId() + ".bpmn";
//                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//                IOUtils.copy(in, response.getOutputStream());
//                response.flushBuffer();
//            }
//        } catch (Exception e) {
//            LOGGER.error("导出model的xml文件失败：modelId={}", modelId, e);
//        }
//    }
//
//    @DeleteMapping("/remove/{ids}")
//    public R remove (@PathVariable("ids") String ids){
//        try {
//            repositoryService.deleteModel(ids);
//            return R.ok("");
//        } catch (Exception e) {
//            return R.failed(e.getMessage());
//        }
//    }
//    /**
//     * 创建模型
//     */
//    @RequestMapping("/create")
//    public void create(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//            RepositoryService repositoryService = processEngine.getRepositoryService();
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            ObjectNode editorNode = objectMapper.createObjectNode();
//            editorNode.put("id", "canvas");
//            editorNode.put("resourceId", "canvas");
//            ObjectNode stencilSetNode = objectMapper.createObjectNode();
//            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
//            editorNode.put("stencilset", stencilSetNode);
//            Model modelData = repositoryService.newModel();
//
//            ObjectNode modelObjectNode = objectMapper.createObjectNode();
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "hello1111");
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
//            String description = "hello1111";
//            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
//            modelData.setMetaInfo(modelObjectNode.toString());
//            modelData.setName("hello1111");
//            modelData.setKey("12313123");
//
//            //保存模型
//            repositoryService.saveModel(modelData);
//            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
//            System.out.println(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
//            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
//        } catch (Exception e) {
//            System.out.println("创建模型失败：");
//        }
//    }
//
//
//}
