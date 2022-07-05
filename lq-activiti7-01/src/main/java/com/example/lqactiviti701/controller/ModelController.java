//package com.example.lqactiviti701.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.repository.Model;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.PNGTranscoder;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Objects;
//
///**
// * @className: ModelController
// * @description: TODO 类描述
// * @author: qing liu
// * @date: 2022/6/18
// **/
//@RestController
//@RequestMapping("/model_locl")
//public class ModelController {
//
//    private static final String MODEL_NAME = "model_name";
//    private static final String MODEL_DESCRIPTION = "model_description";
//    private static final String MODEL_ID = "model_id";
//
//    @Autowired
//    private RepositoryService repositoryService;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @GetMapping("/{modelId}/json")
//    public ObjectNode getEditorJson(@PathVariable String modelId){
//        ObjectNode modelNode = null;
//
//        Model model = repositoryService.getModel(modelId);
//
//        if (model != null) {
//            try {
//                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
//                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
//                } else {
//                    modelNode = objectMapper.createObjectNode();
//                    modelNode.put(MODEL_NAME, model.getName());
//                }
//                modelNode.put(MODEL_ID, model.getId());
//                byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());
//                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(modelEditorSource, StandardCharsets.UTF_8));
//                modelNode.putPOJO("model", editorJsonNode);
//
//            } catch (Exception e) {
//                throw new RuntimeException("Error creating model JSON", e);
//            }
//        }
//        return modelNode;
//    }
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
//}
