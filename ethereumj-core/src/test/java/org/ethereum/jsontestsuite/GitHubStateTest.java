/*
 * Copyright (c) [2016] [ <ether.camp> ]
 * This file is part of the ethereumJ library.
 *
 * The ethereumJ library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ethereumJ library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ethereumJ library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.ethereum.jsontestsuite;

import org.ethereum.config.SystemProperties;
import org.ethereum.config.net.MainNetConfig;
import org.ethereum.jsontestsuite.suite.GeneralStateTestSuite;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GitHubStateTest {

    static String commitSHA = "7f638829311dfc1d341c1db85d8a891f57fa4da7";
    static String treeSHA = "d1ece13ebfb2adb27061ae5a6155bd9ed9773d8f"; // https://github.com/ethereum/tests/tree/develop/GeneralStateTests/
    static GitHubJSONTestSuite.Network[] targetNets = {
            GitHubJSONTestSuite.Network.Frontier,
            GitHubJSONTestSuite.Network.Homestead,
            GitHubJSONTestSuite.Network.EIP150,
            GitHubJSONTestSuite.Network.EIP158,
            GitHubJSONTestSuite.Network.Byzantium
    };

    static GeneralStateTestSuite suite;

    @BeforeClass
    public static void setup() throws IOException {
        suite = new GeneralStateTestSuite(treeSHA, commitSHA, targetNets);
        SystemProperties.getDefault().setRecordInternalTransactionsData(false);
    }

    @AfterClass
    public static void clean() {
        SystemProperties.getDefault().setBlockchainConfig(MainNetConfig.INSTANCE);
        SystemProperties.getDefault().setRecordInternalTransactionsData(true);
    }

    @Test
    public void stSingleTest() throws IOException {
        GeneralStateTestSuite.runSingle(
                "stTransactionTest/TransactionToItself.json", commitSHA, GitHubJSONTestSuite.Network.Frontier);
    }

    @Ignore
    public void stAttackTest() throws IOException {
        suite.runAll("stAttackTest");
    }

    @Ignore
    public void stCallCodes() throws IOException {
        suite.runAll("stCallCodes");
    }

    @Ignore
    public void stExample() throws IOException {
        suite.runAll("stExample");
    }

    @Ignore
    public void stCallDelegateCodesCallCodeHomestead() throws IOException {
        suite.runAll("stCallDelegateCodesCallCodeHomestead");
    }

    @Ignore
    public void stCallDelegateCodesHomestead() throws IOException {
        suite.runAll("stCallDelegateCodesHomestead");
    }

    @Ignore
    public void stChangedEIP150() throws IOException {
        suite.runAll("stChangedEIP150");
    }

    @Ignore
    public void stCallCreateCallCodeTest() throws IOException {

        Set<String> excluded = new HashSet<>();
        excluded.add("CallRecursiveBombPreCall"); // Max Gas value is pending to be < 2^63

        suite.runAll("stCallCreateCallCodeTest", excluded);
    }

    @Ignore
    public void stDelegatecallTestHomestead() throws IOException {
        suite.runAll("stDelegatecallTestHomestead");
    }

    @Ignore
    public void stEIP150Specific() throws IOException {
        suite.runAll("stEIP150Specific");
    }

    @Ignore
    public void stEIP150singleCodeGasPrices() throws IOException {
        suite.runAll("stEIP150singleCodeGasPrices");
    }

    @Ignore
    public void stEIP158Specific() throws IOException {
        suite.runAll("stEIP158Specific");
    }

    @Ignore
    public void stHomesteadSpecific() throws IOException {
        suite.runAll("stHomesteadSpecific");
    }

    @Ignore
    public void stInitCodeTest() throws IOException {
        suite.runAll("stInitCodeTest");
    }

    @Ignore
    public void stLogTests() throws IOException {
        suite.runAll("stLogTests");
    }

    @Ignore
    public void stMemExpandingEIP150Calls() throws IOException {
        suite.runAll("stMemExpandingEIP150Calls");
    }

    @Ignore
    public void stPreCompiledContracts() throws IOException {
        suite.runAll("stPreCompiledContracts");
    }

    @Ignore
    public void stPreCompiledContracts2() throws IOException {
        suite.runAll("stPreCompiledContracts2");
    }

    @Ignore
    public void stMemoryStressTest() throws IOException {
        Set<String> excluded = new HashSet<>();
        excluded.add("mload32bitBound_return2");// The test extends memory to 4Gb which can't be handled with Java arrays
        excluded.add("mload32bitBound_return"); // The test extends memory to 4Gb which can't be handled with Java arrays
        excluded.add("mload32bitBound_Msize"); // The test extends memory to 4Gb which can't be handled with Java arrays
        suite.runAll("stMemoryStressTest", excluded);
    }

    @Ignore
    public void stMemoryTest() throws IOException {
        suite.runAll("stMemoryTest");
    }

    @Ignore
    public void stQuadraticComplexityTest() throws IOException {
        // leaving only Homestead version since the test runs too long
        suite.runAll("stQuadraticComplexityTest", GitHubJSONTestSuite.Network.Homestead);
    }

    @Ignore
    public void stSolidityTest() throws IOException {
        suite.runAll("stSolidityTest");
    }

    @Ignore
    public void stRecursiveCreate() throws IOException {
        suite.runAll("stRecursiveCreate");
    }

    @Ignore
    public void stRefundTest() throws IOException {
        suite.runAll("stRefundTest");
    }

    @Ignore
    public void stReturnDataTest() throws IOException {
        suite.runAll("stReturnDataTest");
    }

    @Ignore
    public void stRevertTest() throws IOException {
        suite.runAll("stRevertTest");
    }

    @Ignore
    public void stSpecialTest() throws IOException {
        suite.runAll("stSpecialTest");
    }

    @Ignore
    public void stStackTests() throws IOException {
        suite.runAll("stStackTests");
    }

    @Ignore
    public void stStaticCall() throws IOException {
        suite.runAll("stStaticCall");
    }

    @Ignore
    public void stSystemOperationsTest() throws IOException {
        suite.runAll("stSystemOperationsTest");
    }

    @Ignore
    public void stTransactionTest() throws IOException {
        // TODO enable when zero sig Txes comes in
        suite.runAll("stTransactionTest", new HashSet<>(Arrays.asList(
                "zeroSigTransactionCreate",
                "zeroSigTransactionCreatePrice0",
                "zeroSigTransacrionCreatePrice0",
                "zeroSigTransaction",
                "zeroSigTransaction0Price",
                "zeroSigTransactionInvChainID",
                "zeroSigTransactionInvNonce",
                "zeroSigTransactionInvNonce2",
                "zeroSigTransactionOOG",
                "zeroSigTransactionOrigin",
                "zeroSigTransactionToZero",
                "zeroSigTransactionToZero2"
        )));
    }

    @Ignore
    public void stTransitionTest() throws IOException {
        suite.runAll("stTransitionTest");
    }

    @Ignore
    public void stWalletTest() throws IOException {
        suite.runAll("stWalletTest");
    }

    @Ignore
    public void stZeroCallsRevert() throws IOException {
        suite.runAll("stZeroCallsRevert");
    }

    @Ignore
    public void stCreateTest() throws IOException {
        suite.runAll("stCreateTest");
    }

    @Ignore
    public void stZeroCallsTest() throws IOException {
        suite.runAll("stZeroCallsTest");
    }

    @Ignore
    public void stZeroKnowledge() throws IOException {
        suite.runAll("stZeroKnowledge");
    }

    @Ignore
    public void stZeroKnowledge2() throws IOException {
        suite.runAll("stZeroKnowledge2");
    }

    @Ignore
    public void stCodeSizeLimit() throws IOException {
        suite.runAll("stCodeSizeLimit");
    }

    @Ignore
    public void stRandom() throws IOException {
        suite.runAll("stRandom");
    }

    @Ignore
    public void stRandom2() throws IOException {
        suite.runAll("stRandom2");
    }

    @Ignore
    public void stBadOpcode() throws IOException {
        suite.runAll("stBadOpcode");
    }

    @Ignore
    public void stNonZeroCallsTest() throws IOException {
        suite.runAll("stNonZeroCallsTest");
    }

    @Ignore
    public void stCodeCopyTest() throws IOException {
        suite.runAll("stCodeCopyTest");
    }
}

